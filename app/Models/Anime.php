<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Attributes\Fillable;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Model;

#[Fillable(['title', 'japanese_title', 'synopsis', 'type', 'episodes', 'status', 'aired_from', 'aired_to', 'score', 'image_source'])]
class Anime extends Model
{
    use HasUuids;

    protected $casts = [
        'aired_from' => 'date',
        'aired_to' => 'date',
        'score' => 'decimal:2'
    ];
}
