<?php

namespace App\Http\Controllers;

use App\Models\Anime;

class AnimeController extends Controller
{
    public function index()
    {
        $animes = Anime::all();

        return view('anime.index')
            ->with('animes', $animes);
    }
}
