<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Concerns\HasUuids;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Genre extends Model
{
    use HasUuids;

    public function animes(): BelongsToMany
    {
        return $this->belongsToMany(Anime::class);
    }
}
