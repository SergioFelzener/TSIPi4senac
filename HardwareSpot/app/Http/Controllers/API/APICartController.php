<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Cart;
use App\Models\Product;
use Illuminate\Http\Request;
use App\Models\ProductOrder;
use App\Models\Order;



class APICartController extends Controller
{

    public function cart()
    {
        $user = auth()->user();

        $cart = $user->cart;

        if ($cart->count() > 0) {

            $products = [];

            foreach ($cart as $c) {

                $product = Product::find($c->product_id);
                //dd($c);

                //dd($c);
                $amount = ($c->amount);
                $product->amount = $amount;
                array_push($products, $product);
            }

            //dd($products);

            return response()->json(['products' => $products]);

        } else {

            return response()->json(["error" => "Não há produtos no carrinho"], 400);
        }
    }

    public function store(Request $request)
    {

        if (!$request->product_id) {

            return response()->json(["error" => "Requisição com corpo incompleto"], 400);
        }

        $prod = Product::all()->where('id', $request->product_id)->count();

        if ($prod > 0) {

            $user = auth()->user()->id;

            $checkCart = Cart::all()->where('user_id', $user)->where('product_id', $request->product_id)->first();

            if ($checkCart) {

                $amount = $checkCart->amount;

                $checkCart->update(['amount' => $amount + 1]);

                return response()->json(["success" => "Produto adicionado no carrinho"]);
            } else {

                Cart::create([
                    'user_id' => $user,
                    'product_id' => $request->product_id,
                    'amount' => $request->amount
                ]);

                return response()->json(["success" => "Produto adicionado no carrinho"]);
            }
        } else {

            return response()->json([
                "error" => "Este Produto não existe na base de dados",
                "product_id" => "Is invalid",
                "mesage" => "Impossível adicionar no carrinho"
            ], 401);
        }
    }

    public function removeProdOne(Request $request)
    {

        if (!$request->product_id) {

            return response()->json(["Error" => "Dados incompletos"], 400);
        }

        $user = auth()->user()->id;

        $checkCart = Cart::all()->where('user_id', $user)->where('product_id', $request->product_id)->first();

        if ($checkCart == null) {

            return response()->json(["Error" => "Carrinho não encontrado"], 404);
        }

        if ($checkCart->amount > 1) {

            $amount = $checkCart->amount;

            $checkCart->update(['amount' => $amount - 1]);

        } else {

            $checkCart->delete();
        }
        $product = Product::findOrFail($checkCart->product_id);
        return response()->json(["success" => "{$product->name} uma unidade removida com sucesso"]);
    }

    public function removeProd(Request $request)
    {

        if (!$request->product_id) {

            return response()->json(["Error" => "Dados incompletos"], 400);
        }

        $user = auth()->user()->id;

        $checkCart = Cart::all()->where('user_id', $user)->where('product_id', $request->product_id)->first();

        if ($checkCart == null) {

            return response()->json(["Error" => "Carrinho não encontrado"], 404);
        }

        if ($checkCart->amount > 0) {
            $checkCart->delete();
        }
        $product = Product::findOrFail($checkCart->product_id);
        return response()->json(["success" => "{$product->name} removido do carrinho com sucesso"]);
    }



    public function removeCart()
    {

        $user = auth()->user()->id;

        $this->delete($user);

        return response()->json(["success" => "Carrinho Vazio"]);
    }

    private function delete(int $user)
    {

        //remove produto do carrinho do cliente
        $removeProducts = Cart::all()->where('user_id', $user);

        foreach ($removeProducts as $products) {
            $products->delete();
        }
    }

    public function checkout()
    {

        $user = auth()->user()->id;

        $orderItems = Cart::all()->where('user_id', $user);

        // verificar estoque do produto
        foreach ($orderItems as $prod) {

            $product = Product::find($prod->product_id);
            $quantity = $product->quantity;

            if ($prod->amount > $quantity) {
                return response()->json([
                    "ERRO" => "$product->name não tem em estoque",
                    "Quantidade em estoque" => $quantity,
                    "Mesage" => "Pedido não realizado"
                ], 303);
            }
        }

        //criando pedido
        $order = Order::create(['user_id' => $user]);

        // inserir as orders
        $this->insertOrder($user, $order->id);

        //removendo produtos do carrinho
        $this->delete($user);

        return response()->json(["success" => "Parabéns! Compra finalizada com sucesso!"]);
    }

    private function insertOrder(int $user, int $order)
    {
        // Pegar carrinho do usuário
        $orderItems = Cart::all()->where('user_id', $user);

        foreach ($orderItems as $prod) {
            $product = Product::find($prod->product_id);

            // Criando registo na tabela de order
            ProductOrder::create([
                'order_id' => $order,
                'product_id' => $product->id,
                'price' => $product->price - ($product->price * ($product->descount / 100) * $prod->amount),
                'amount' => $prod->amount
            ]);

            // remove o produto do estoque
            $quantity = $product->quantity;
            $quantity -= $prod->amount;


            //atualizando produto
            $product->update(['quantity' => $quantity]);
        }
    }
}
