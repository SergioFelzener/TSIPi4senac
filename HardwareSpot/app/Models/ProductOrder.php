<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ProductOrder extends Model
{
    use HasFactory;  //Tabela PIVO de Order

    protected $fillable = ['order_id', 'product_id', 'price', 'amount'];

    public function product() {

        return $this->belongsTo(Product::class);

    }

    public function order() {

        return $this->hasOne(Product::class);

    }


}
