<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Lista de Categorias
        </h2>
        @if (session('success'))
        <div class="bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md" role="alert">
            <div class="flex">
                <div class="py-1"><svg class="fill-current h-6 w-6 text-teal-500 mr-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                        <path d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z" /></svg></div>
                {{session('success')}}
            </div>
        </div>
        @endif
    </x-slot>

    <div class="mt-5 ml-6">
        <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded" href="{{route('admin.categories.create')}}">New Category</a>
    </div>

    <div class="max-w-76x1 mx-auto px-6 ">
        <table class="table-auto w-full">
            <thead>
                <tr>
                    <th class="px-4 py-2">Category</th>
                    <th class="px-4 py-2">Description</th>
                    <th class="px-4 py-2">Slug</th>
                    <th class="px-4 py-2">Actions</th>
                </tr>
            </thead>
            <tbody class="divide-y divide-blue-300">
                @foreach($categories as $category)
                <tr>
                    <td class="border px-4 py-2">
                        <div class="flex px-4">
                            <div class="flex-shrink h-10 w-10">
                                <img src="http://via.placeholder.com/50" alt="" class="rounded-full h10 w-10 mr-3 ">
                            </div>
                            <div class="ml-3">
                                <span class="block">{{$category->name}}</span>
                                <span class="block text-gray-300 tx-sm">Categoria</span>
                            </div>
                        </div>
                    </td>
                    <td class="border px-4 py-2">{{$category->description}}</td>
                    <td class="border px-4 py-2">{{$category->slug}}</td>
                    <td class="border px-4 py-2">
                        <div class="inline-flex">
                            <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 mr-2 rounded"><a href="{{route('admin.categories.edit', $category->id)}}">Edit</a></button>
                            <form action="{{route('admin.categories.destroy', ['category' => $category->id])}}" method="POST">
                                @csrf
                                @method("DELETE")
                                <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
                @endforeach

        </table>
    </div>

</x-app-layout>
