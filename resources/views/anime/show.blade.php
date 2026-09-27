@extends('layouts.page')

@section('content')
    <main class="bg-neutral-900 flex gap-4 m-4 p-4">
        <section>
            <img alt="{{$anime->title}}" src="{{$anime->image_source}}"/>
        </section>
        <section class="flex flex-1 flex-col gap-4">
            <section>
                <h2 class="font-bold text-2xl text-justify text-neutral-50">
                    {{ $anime->title }}
                </h2>
                <h3 class="font-bold text-neutral-50 text-md">{{ $anime->japanese_title }}</h3>
            </section>
            <section class="flex gap-4 text-sm">
                <p class="bg-neutral-800 px-2 py-1 text-neutral-50">Score: {{$anime->score}}</p>
                @unless($anime->type == 'Movie')
                    <p class="bg-neutral-800 px-2 py-1 text-neutral-50">Episodes: {{$anime->episodes}}</p>
                @endunless
                <p class="bg-neutral-800 px-2 py-1 text-neutral-50">{{$anime->type}}</p>
            </section>
            <section class="flex gap-4 text-sm">
                <p class="bg-neutral-800 px-2 py-1 text-neutral-50 text-sm">{{$anime->status}}</p>
                <p class="bg-neutral-800 px-2 py-1 text-neutral-50 text-sm">
                    Aired:
                    {{ $anime->aired_from?->translatedFormat('F d, Y') ?? 'Unknown' }}
                    to
                    {{ $anime->aired_from?->translatedFormat('F d, Y') ?? 'Unknown' }}
                </p>
            </section>
            <section class="flex flex-1 flex-col gap-2">
                <h4 class="text-neutral-50 text-xl">Synopsis</h4>
                <hr class="text-neutral-50">
                <p class="text-justify text-neutral-50 text-md">
                    {{$anime->synopsis}}
                </p>
            </section>
            <section class="flex flex-1 flex-col gap-2">
                <h4 class="text-neutral-50 text-xl">Genres</h4>
                <hr class="text-neutral-50">
                <div class="flex gap-2">
                    @foreach($anime->genres as $genre)
                        <p class="bg-neutral-800 text-neutral-50 px-2 py-1">{{$genre->name}}</p>
                    @endforeach
                </div>
            </section>
        </section>
    </main>
@endsection
