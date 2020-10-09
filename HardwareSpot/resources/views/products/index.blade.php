<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Lista de Produtos
        </h2>
    </x-slot>

    <div class="max-w-76x1 mx-auto px-6 ">
        <table class="table-auto w-full">
            <thead>
                <tr>
                    <th class="px-4 py-2">Product</th>
                    <th class="px-4 py-2">Description</th>
                    <th class="px-4 py-2">Price</th>
                    <th class="px-4 py-2">Actions</th>
                </tr>
            </thead>
            <tbody class="divide-y divide-blue-300">
                @foreach($products as $product)
                <tr>
                    <td class="border px-4 py-2">
                        <div class="flex px-4">
                            <div class="flex-shrink h-10 w-10">
                                <img src="http://via.placeholder.com/50" alt="" class="rounded-full h10 w-10 mr-3 ">
                            </div>
                            <div class="ml-3">
                                <span class="block">{{$product->name}}</span>
                                <span class="block text-gray-300 tx-sm">Categoria</span>
                            </div>
                        </div>
                    </td>
                    <td class="border px-4 py-2">{{$product->description}}</td>
                    <td class="border px-4 py-2">{{$product->price}}</td>
                    <td class="border px-4 py-2">
                        <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"><a href="{{route('products.edit', $product->id)}}">Edit</a></button>
                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Show</button>
                        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Delete</button>
                    </td>
                </tr>
                @endforeach

        </table>
    </div>
    <div class="mt-5 ml-6">
        <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded" href="{{route('products.create')}}">New Product</a>
    </div>


</x-app-layout>
