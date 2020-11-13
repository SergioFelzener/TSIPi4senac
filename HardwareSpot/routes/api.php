<?php

use App\Http\Controllers\API\APICartController;
use App\Http\Controllers\API\APICategoriesController;
use App\Http\Controllers\API\APIProductsController;
use App\Http\Controllers\API\APIUserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['middleware' => 'auth:sanctum'], function(){
    Route::get('users', [APIUserController::class, 'index'])->name('users');
    Route::get('/cart', [APICartController::class, 'cart']);
    Route::post('/cart', [APICartController::class, 'store']);
});

Route::post('/login', [APIUserController::class, 'login']);
Route::post('/register', [APIUserController::class, 'store']);
Route::get('/products', [APIProductsController::class, 'index']);
Route::get('categories', [APICategoriesController::class, 'index']);




