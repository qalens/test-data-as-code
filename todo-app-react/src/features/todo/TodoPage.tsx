import {TodoList} from "./TodoList";
import {AddTodo} from "./AddTodo";

export function TodoPage(){
    return (
        <div>
            <AddTodo/>
            <TodoList/>
        </div>
    )
}