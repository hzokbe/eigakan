<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('animes', function (Blueprint $table) {
            $table->uuid('id')->primary();

            $table->string('title');

            $table->string('japanese_title')->nullable();

            $table->text('synopsis')->nullable();

            $table->enum('type', ['TV', 'Movie', 'OVA']);

            $table->unsignedInteger('episodes')->nullable();

            $table->enum('status', ['Finished Airing', 'Currently Airing', 'Not yet aired']);

            $table->date('aired_from')->nullable();

            $table->date('aired_to')->nullable();

            $table->decimal('score', 4)->nullable();

            $table->string('image_source')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('animes');
    }
};
