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

    public function product() {
        return $this->hasMany(Category::class);
    }

    public function products() {
        return $this->belongsToMany(Product::class)->withPivot('image');
    }

    public function getCategories(){
        return response()->json(Category::with('product','photos')->get());

    } 
}
