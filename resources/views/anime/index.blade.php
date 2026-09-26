@extends('layouts.page')

@section('content')
    <main class="box-border flex flex-col gap-2 p-4">
        <x-search-bar/>
        @foreach($animes as $anime)
            <x-anime-card :anime="$anime"/>
        @endforeach
    </main>
@endsection
