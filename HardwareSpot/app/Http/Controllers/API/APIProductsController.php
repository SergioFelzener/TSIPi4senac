<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Category;
use Illuminate\Http\Request;
use App\Models\Product;

class APIProductsController extends Controller
{
    private $product;

    public function __construct(Product $product){
        $this->product = $product;

    }
    
    public function index()
    {
        return response()->json(Product::with('categories','photos')->get());
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
        $product = Product::find($id);

        //dd($product);

        //verificando se tem protudo
        if ($product) { 

            return response()->json([
                'product' => $product, 
                'photos' => $product->photos()->get(),
                'categories' => $product->categories()->get(),
                'success' => 'Produto' . $product . ' - Está cadastrado no sistema'     
            ]);

        }else { 

            return response()->json(['Error' => 'Produto não existe na base de dados'], 404);

        }

        //dd($product::with('categories','photos')->first());

        
    }

    public function searchProducts($product) { 

        $products = Product::selectRaw('products.*')->where('products.name', 'LIKE' , '%' . $product . '%')->orderBy('name')->get();
        //dd($products);

        if ($products->count() > 0 ) { 

            return response()->json(['product' => $products,
                                     'photos' => ' ']); 
        } else { 

            return response()->json(['Error' => 'Produto não Existe da Base de Dados'], 404);
        }

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
