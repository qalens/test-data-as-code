import React from 'react';
import './App.css';
import {TodoPage} from "./features/todo/TodoPage";
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <TodoPage/>
      </header>
    </div>
  );
}

export default App;
