<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Spatie\Sluggable\HasSlug;
use Spatie\Sluggable\SlugOptions;


class Product extends Model
{
    use HasFactory;
    use HasSlug;

    protected $fillable = ['name', 'description', 'price', 'descount', 'quantity', 'slug'];

    public function getSlugOptions() : SlugOptions {

        return SlugOptions::create()->generateSlugsFrom('name')->saveSlugsTo('slug');

    }

    public function categories() {
        return $this->belongsToMany(Category::class);
    }

    public function photos(){
        return $this->hasMany(ProductPhoto::class);
    }

    public function getCategories(){
        return response()->json(Category::with('product','photos')->get());

    }
    
    public function cart(){ 

        return $this->belongsToMany(Cart::class)->withPivot('amount');

    }
}
