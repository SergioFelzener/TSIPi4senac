<?php

namespace Database\Factories;

use App\Models\Product;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Facades\DB;



class ProductFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = Product::class;
    

    public function definition()
    {
        return
            [
                'name' => $this->faker->name,
                'description' => $this->faker->sentence,
                'price' => $this->faker->randomFloat(2, 1, 10),
                'descount' => 5,
                'quantity' => 10,
                'slug' => $this->faker->slug

            ];
        
    }
}
