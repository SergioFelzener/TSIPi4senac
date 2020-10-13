<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Product;

class APIProductsController extends Controller
{
    public function getProductsAll(){
        $products = Product::all();

        return response()->json($products);
    }
    
    public function getProduct($id){
        $product = Product::find($id);
        if($product){
            return response()->json($product, 200);
        } else{
            return json_encode([$id => 'Produto não existe']);
        }
        
    }
}
