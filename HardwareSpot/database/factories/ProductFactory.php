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
                'name' => $this->faker->word,
                'description' => $this->faker->words(3, true),
                'price' => $this->faker->randomFloat(2, 1, 100),
                'descount' => $this->faker->numberBetween(5, 25),
                'quantity' => $this->faker->numberBetween(10, 100),
                'slug' => $this->faker->slug

            ];
        
    }
}
