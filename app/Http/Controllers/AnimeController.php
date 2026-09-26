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

        $query = Anime::query();

        if ($request->input('query', '') != '') {
            $query->where('title', 'like', '%' . $request->input('query') . '%');
        }

        $query->orderBy($sort, $direction);

        $animes = $query->get();

        return view('anime.index')
            ->with('animes', $animes);
    }
}
