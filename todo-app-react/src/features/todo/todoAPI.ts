import {TodoItem, TodoStatus} from "./todoSlice";
export function refreshItems() {
  return new Promise<TodoItem[]>((resolve) => {
          return fetch('http://localhost:8731/todo/', {
              headers: new Headers({'content-type': 'application/json'}),
          })
              .then(response => {
                  if (!response.ok) {
                      throw new Error(response.statusText)
                  }
                  resolve(response.json())
              }).catch(err => {
                  console.log(err)
              });
      }
    // setTimeout(() => resolve({ data: data }), 500)
  );
}
export interface AddTodoItem{
  name:string,
  tags:number[],
  status:TodoStatus
}
export interface PatchTodoItem{
  name?:string,
  tags?:number[],
  status?:TodoStatus
}
export function addTodo(data:AddTodoItem){
  return new Promise<TodoItem>((resolve)=>{
      return fetch('http://localhost:8731/todo/',{
          method:"POST",
          body:JSON.stringify(data),
          headers: new Headers({'content-type': 'application/json'}),
      })
          .then(response => {
              if (!response.ok) {
                  throw new Error(response.statusText)
              }
              resolve(response.json())
          }).catch(err=>{
          console.log(err)
      })
  })
}
export function patchTodo(id:number, data:PatchTodoItem){
  return new Promise<TodoItem>((resolve)=>{
      return fetch(`http://localhost:8731/todo/${id}/`,{
          method:"PATCH",
          body:JSON.stringify(data),
          headers: new Headers({'content-type': 'application/json'}),
      })
          .then(response => {
              if (!response.ok) {
                  throw new Error(response.statusText)
              }
              resolve(response.json())
          }).catch(err=>{
          console.log(err)
      })
  })
}

