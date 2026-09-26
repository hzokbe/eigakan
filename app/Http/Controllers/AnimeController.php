<?php

namespace App\Http\Controllers;

use App\Models\Anime;
use Illuminate\Http\Request;

class AnimeController extends Controller
{
    public function index(Request $request)
    {
        $allowedSorts = [
            'title',
            'score',
            'aired_from'
        ];

        $sort = $request->input('sort', 'score');

        if (!in_array($sort, $allowedSorts)) {
            $sort = 'score';
        }

        $direction = $request->input('direction', 'desc');

        if (!in_array($direction, ['asc', 'desc'])) {
            $direction = 'desc';
        }

        $animes = Anime::query()
            ->orderBy($sort, $direction)
            ->get();

        return view('anime.index')
            ->with('animes', $animes);
    }
}
