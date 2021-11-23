import {TagItem} from "./tagSlice";
export function refreshItems(data:TagItem[] = [{
  name:"Kam Kar",
  id:1,
}]) {
  return new Promise<TagItem[] >((resolve) => {
          return fetch('http://localhost:8731/tag/', {
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
export interface AddTagItem{
  name:string
}
export function addTag(name:string){
  return new Promise<TagItem>((resolve)=>{
      return fetch('http://localhost:8731/tag/',{
          method:"POST",
          body:JSON.stringify({name}),
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
    // setTimeout(()=>resolve({item:{...data,id:1}}))
  })
}
