<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    use HasFactory;

    protected $fillable = ['user_id'];

    // usuário dono do pedido
    public function user(){

        return $this->belongsTo(User::class);

    }

    // Order has many products (Produtos da order)

    public function order() {

        return $this->hasMany(Products::class);
    }
}
