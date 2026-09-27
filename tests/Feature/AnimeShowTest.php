<?php

use Database\Factories\AnimeFactory;
use Database\Factories\GenreFactory;


test('show anime details', function () {
    $id = (string)Str::uuid();

    $anime = AnimeFactory::new()->create(['id' => $id]);

    $genres = GenreFactory::new()
        ->count(2)
        ->sequence(
            ['name' => 'Drama'],
            ['name' => 'Fantasy'],
        )
        ->create();

    $anime
        ->genres()
        ->attach($genres->map(function ($genre) {
            return $genre->id;
        }));

    $response = $this->get('/animes/' . $id);

    $viewAnime = $response->viewData('anime');

    expect($viewAnime->id)->toBe($id)->and($viewAnime->genres->pluck('name')->sort()->values()->toArray())->toBe(['Drama', 'Fantasy']);
});
