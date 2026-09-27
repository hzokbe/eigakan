<?php

use Database\Factories\AnimeFactory;

test('list all animes without query', function () {
    $animes = AnimeFactory::new()
        ->count(3)
        ->create();

    $response = $this->get('/animes');

    $response
        ->assertStatus(200)
        ->assertViewIs(('anime.index'))
        ->assertViewHas('animes', function ($viewAnimes) use ($animes) {
            return $viewAnimes->count() === $animes->count();
        });
});

test('list all animes with query', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Sousou no Frieren'],
            ['title' => 'Steins;Gate'],
            ['title' => 'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen'],
        )->create();

    $response = $this->get('/animes?query=Steins;Gate');

    $response
        ->assertStatus(200)
        ->assertViewIs(('anime.index'))
        ->assertViewHas('animes', function ($viewAnimes) {
            return $viewAnimes->count() === 1;
        });
});

test('list animes ordered by title ascending', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Steins;Gate'],
            ['title' => 'Sousou no Frieren'],
            ['title' => 'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen'],
        )->create();

    $response = $this->get('/animes?sort=title&direction=asc');

    $response
        ->assertStatus(200)
        ->assertViewIs('anime.index')
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen',
                    'Sousou no Frieren',
                    'Steins;Gate',
                ];
        });
});

test('list animes ordered by title descending', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Steins;Gate'],
            ['title' => 'Sousou no Frieren'],
            ['title' => 'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen'],
        )->create();

    $response = $this->get('/animes?sort=title&direction=desc');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'Steins;Gate',
                    'Sousou no Frieren',
                    'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen',
                ];
        });
});

test('list animes ordered by score', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Low', 'score' => 5.0],
            ['title' => 'High', 'score' => 9.5],
            ['title' => 'Mid', 'score' => 7.0],
        )->create();

    $response = $this->get('/animes?sort=score&direction=desc');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'High',
                    'Mid',
                    'Low',
                ];
        });
});

test('list animes ordered by aired_from', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Newest', 'aired_from' => now()->subYear()],
            ['title' => 'Oldest', 'aired_from' => now()->subYears(10)],
            ['title' => 'Middle', 'aired_from' => now()->subYears(5)],
        )->create();

    $response = $this->get('/animes?sort=aired_from&direction=asc');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'Oldest',
                    'Middle',
                    'Newest',
                ];
        });
});

test('falls back to score desc when sort field is not allowed', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Low', 'score' => 5.0],
            ['title' => 'High', 'score' => 9.5],
            ['title' => 'Mid', 'score' => 7.0],
        )->create();

    $response = $this->get('/animes?sort=invalid_column');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'High',
                    'Mid',
                    'Low',
                ];
        });
});

test('falls back to desc when direction is invalid', function () {
    AnimeFactory::new()
        ->count(3)
        ->sequence(
            ['title' => 'Low', 'score' => 5.0],
            ['title' => 'High', 'score' => 9.5],
            ['title' => 'Mid', 'score' => 7.0],
        )->create();

    $response = $this->get('/animes?sort=score&direction=invalid');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'High',
                    'Mid',
                    'Low',
                ];
        });
});

test('defaults to score desc when no sort params are given', function () {
    AnimeFactory::new()->count(3)->sequence(
        ['title' => 'Low', 'score' => 5.0],
        ['title' => 'High', 'score' => 9.5],
        ['title' => 'Mid', 'score' => 7.0],
    )->create();

    $response = $this->get('/animes');

    $response
        ->assertViewHas('animes', function ($animes) {
            return $animes->pluck('title')->values()->all() === [
                    'High',
                    'Mid',
                    'Low',
                ];
        });
});
