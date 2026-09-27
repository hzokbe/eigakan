<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Attributes\Fillable;
use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

#[Fillable(['title', 'japanese_title', 'synopsis', 'type', 'episodes', 'status', 'aired_from', 'aired_to', 'score', 'image_source'])]
class Anime extends Model
{
    use HasUuids;

    public $incrementing = false;

    protected $casts = [
        'aired_from' => 'date',
        'aired_to' => 'date',
        'score' => 'decimal:2'
    ];

    protected $keyType = 'string';

    public function genres(): BelongsToMany
    {
        return $this->belongsToMany(Genre::class);
    }
}
