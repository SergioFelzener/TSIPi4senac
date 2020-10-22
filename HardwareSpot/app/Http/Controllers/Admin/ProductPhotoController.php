<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\ProductPhoto;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;
use App\Models\Product;

class ProductPhotoController extends Controller
{
    public function removePhoto(Request $request){


        $photoName = $request->get('photoName');
        //Buscar a foto no banco pelo ID
        if(Storage::disk('public')->exists($photoName)) {
            //se existir Remova dos arquivos
            Storage::disk('public')->delete($photoName);
        }
        //Remova a imagem do banco
        $removePhoto = ProductPhoto::where('image', $photoName);
        $productId = $removePhoto->first()->product_id;
        $removePhoto->delete();

        return redirect(route('admin.products.edit', ['product' => $productId]))->with('success', 'Product Image Deleted');
    }
}
