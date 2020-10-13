<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Product;
use App\Models\Category;

class ProductController extends Controller
{
    private $product;

    public function __construct(Product $product){
        $this->product = $product;

    }
    public function index() {

        $products = $this->product->paginate(25);

        return view('admin.products.index', compact('products'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(){

        $categories = Category::all(['id', 'name']);

        return view('admin.products.create', compact('categories'));

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request){


        //verificando retorno de photos upload
        //dd($request->file('photos'));
        //$images = $request->file('photos');
        //foreach($images as $image) {
        //    print $image->store('products', 'public') . '<br>';
        //}
        //dd('Ok Upload');

        $data = $request->all();

        $product = Product::create($data);
        $product->categories()->sync($data['categories']);

        if ($request->hasFile('photos')) {
            $images = $this->imageUpload($request, 'image');

            // inserindo a referencia da imagem no DB.

            $product->photos()->createMany($images);

        }



        return redirect(route('admin.products.index'))->with('success', 'Product has been created');

        //$data = $request->all();
        //dd($data);
        //Product::create($request->all());
        //$product = $this->create($request->all());
        //dd($product);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $product
     * @return \Illuminate\Http\Response
     */
    public function show($product)
    {
        return $product;
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $product
     * @return \Illuminate\Http\Response
     */
    public function edit($product)
    {
        $product = $this->product->findOrFail($product);
        $categories = Category::all(['id', 'name']);

        return view('admin.products.edit', compact('product', 'categories'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $product
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $product){

        $data = $request->all();

        $product = $this->product->find($product);

        $product->update($data);

        $product->categories()->sync($data['categories']);

        if ($request->hasFile('photos')) {
            $images = $this->imageUpload($request, 'image');

            // inserindo a referencia da imagem no DB.

            $product->photos()->createMany($images);

        }

        return redirect(route('admin.products.index'))->with('success', 'Product updated successfully');




        //$product->update([
        //    'name' => $request->name,
        //    'description' => $request->description,
        //    'price' => $request->price,
        //    'descount' => $$request->descount,
        //    'quantity' => $request->quantity,
        //    'slug' => $request->slug
        //]);

        //session()->flash('success', 'product updated successfully');
        //return redirect(route('admin.products.index'));
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $product
     * @return \Illuminate\Http\Response
     */
    public function destroy($product){

        $product = $this->product->find($product);

        $product->delete();

        return redirect(route('admin.products.index'))->with('success', 'Product Deleted');
    }

    private function imageUpload(Request $request, $imageColumn){

        $images = $request->file('photos');

        $uploadedImages = [];

        foreach($images as $image) {
            $uploadedImages[] =  [$imageColumn => $image->store('products', 'public')];
        }

        return $uploadedImages;

    }
}
