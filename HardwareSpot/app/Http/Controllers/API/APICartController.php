<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Product;
use Illuminate\Http\Request;
use App\Models\ProductOrder;
use App\Models\Cart;
use App\Models\Order;

class APICartController extends Controller
{

    public function cart(){

        $user = auth()->user();
        $cart = $user->cart;

        if ($cart->count() > 0) {
            $products = [];

            foreach($cart as $c) {

                $prod = Product::all()->find($c->product_id);
                array_push($c, $prod);
            }

            return response()->json($products);

        }else {

            return response()->json(["error" => "Carrinho etá Vazio"]);
        }
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(){
    
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request){

        if(!$request->product_id) {
            return response()->json(["Erro" => "Requisição Incompleta"]);
        }

        $prod = Product::all()->where('id', $request->product_id)->count();

        if($prod > 0 ) { 
            $userId = auth()->user()->id;

            $checkCart = Cart::all()->where('user_id', $userId)->where('product_id', $request->product_id)->first();

            if ($checkCart) {

                $amount = $checkCart->amount;

                $checkCart->update(['amount' => $amount]);

                return response()->json(["success" => "Produto adiconado ao carrinho"]);

            } else { 

                Cart::create([
                    'user_id' => $userId,
                    'product_id' => $request->product_id,
                    'amount' => 1

                ]);

                return response()->json(["success" => "Produto adicionado no carrinho"]); 

            } 
        }else { 

            return response()->json(["Error" => "Produto inválido"], 400);
        }
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
