<?php

namespace Database\Factories;

use App\Models\Anime;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Anime>
 */
class GenreFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => fake()->randomElement([
                'Drama',
                'Fantasy',
                'Sci-Fi',
            ]),
        ];
    }
}
