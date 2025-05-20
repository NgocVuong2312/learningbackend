<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\View\View;

abstract class Controller
{
    public function index(): View
    {
        $users = DB::select('SELECT * FROM bookdb.bookinfodb');
 
        return view('user.index', ['users' => $users]);
    }
}