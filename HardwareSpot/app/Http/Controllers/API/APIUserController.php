<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;


class APIUserController extends Controller
{
    public function login(Request $request){

        $request->validate([
            'email' => 'required',
            'password' => 'required',
            'device_name' => 'required'
        ]);

        $user = User::where('email', $request->email)->first();

        
        if (!$user || !Hash::check($request->password, $user->password)) {
             return response()->json([["error" => "Credenciais incorretas."]], 401);         
        }

        return response()->json([
            'user' => $user,
            'status' => "Sucesso",
            'msg' => $user->email . " " . "Logado no sistema", 
            'id' => $user->id,
            'token' => $user->createToken($request->device_name)->plainTextToken]);

    }

    public function logout(Request $request){

        if ($request->user()->tokens()->delete()){
            return response()->json(["success" => "Logout executato com sucesso"]);
        } else {
            return response()->json(["error" => "Problemas ao executar o LogOut"], 409);
        }

    }

    public function index()
    {
        $users = User::all();

        return response()->json($users);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request){
        if (!$request->name || !$request->email || !$request->password) {
            return response()->json(["error" => "Dados Inválidos"], 400);
        }

        $user = User::where('email', $request->email);
        if ($user->count() > 0) {
            return response()->json(["error" => "E-mail já utilizado"], 401);
        }

        $user = User::create([

            'name' => $request->name,
            'email' => $request->email,
            'password' => Hash::make($request->password),

        ]);

        return response()->json(['user' => $user, 
        'token' => $user->createToken($request->email)->plainTextToken]);
        
    }

    
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $user = User::find($id);
        $user->update([
            'name' => $request->name
        ]);
        
        if($user->email != $request->email){
            $user->email = $request->email;
            $user->email_verified_at = null;
        }
        if($request->password)
            $user->password = Hash::make($request->password);
        $user->save();

        return response()->json(["message" => "Usuário atualizado com sucesso", "user" => $user] );
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
