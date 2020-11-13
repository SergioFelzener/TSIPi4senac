<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class CartController extends Controller{


    public function index(){
        
        $cart = session()->has('cart') ? session()->get('cart') : [];

        return view('cart', compact('cart'));
    }

    public function add(Request $request) {

        $product = $request->get('product');
        
        // dd($product);
        // dd(session());

        // verificar se existe sessao para produtos
        if(session()->has('cart')) {

            session()->push('cart', $product);

        } else {

            $products[] = $product;

            session()->put('cart', $product);
        }
        // dd($product);
        flash('ADD')->success();
        return redirect()->route('product.single', ['slug' => $product['slug']]);



        // existindo eu adicono esse produto na sessao 



        // nao existindo criar sessao com o produto



        
    }
    
     
     
    


}
