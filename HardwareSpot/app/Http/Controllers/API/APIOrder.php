<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Order;
use App\Models\Product;
use App\Models\ProductOrder;


class APIOrder extends Controller
{

    private function userOrders(int $id) {

        return Order::all()->where('user_id', $id);
    }

    private function orderProduct(int $id) {

        return ProductOrder::all()->where('order_id', $id);

    }

    public function myOrders(){

        $userId = auth()->user()->id;

        $orders = $this->userOrders($userId);

        //dd($orders);

        if ($orders->count() > 0 ) { 

            return response()->json(['orders' => $orders]);

        } else {

            return response()->json(["Error" => "Não tem pedidos"]);
        }
    }

    public function showProducts($id) {

        //Produtos do pedido
        $orderProduct = $this->orderProduct($id);

        $total = number_format($orderProduct->sum('price'), 2, ',', '');

        foreach($orderProduct as $product){ 

            $userProducts[] = Product::find($product->product_id);

            $price[] = $product->price;

            $amount[] = $product->amount;

        }

        return response()->json([
            'products' => $userProducts, 
            'price' => $price,
            'amount' => $amount,
            'total' => $total
        ]);
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
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
