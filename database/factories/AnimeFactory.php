<?php

namespace Database\Factories;

use App\Models\Anime;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Anime>
 */
class AnimeFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'title' => fake()->randomElement([
                'Sousou no Frieren',
                'Steins;Gate',
                'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen'
            ]),
            'type' => fake()->randomElement(['TV', 'Movie']),
            'status' => fake()->randomElement(['Finished Airing', 'Currently Airing', 'Not yet aired'])
        ];
    }
}
