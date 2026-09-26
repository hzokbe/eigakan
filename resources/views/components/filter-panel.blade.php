@php
    $sort = request('sort', 'score');

    $direction = request('direction', 'asc');
@endphp

<div class="bg-neutral-900 flex gap-4 justify-end p-4 text-neutral-50 text-sm">
    <form class="flex gap-1" method="get" action="/animes">
        <select class="bg-neutral-800 px-4 py-1 pr-2" name="sort">
            <option value="title" @selected($sort === 'title')>Title</option>
            <option value="score" @selected($sort === 'score')>Score</option>
            <option value="aired_from" @selected($sort === 'aired_from')>Aired at</option>
        </select>
        <select class="bg-neutral-800 px-4 py-1" name="direction">
            <option value="asc" @selected($direction === 'asc')>Ascending</option>
            <option value="desc" @selected($direction === 'desc')>Descending</option>
        </select>
        <button class="bg-blue-600 cursor-pointer font-bold px-4 py-1" type="submit">Apply</button>
        <a class="bg-neutral-50 cursor-pointer flex font-bold items-center text-neutral-950 px-4 py-1"
           href="/animes">Clear</a>
    </form>
</div>
