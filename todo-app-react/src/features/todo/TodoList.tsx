import {useAppDispatch, useAppSelector} from "../../app/hooks";
import {addAsync, refreshAsync, archiveAsync,selectTodos, TodoWithTagDetails, TodoStatus} from "./todoSlice";
import {refreshAsync as refreshTags} from "../tag/tagSlice";
import {useEffect, useState} from "react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import {
    faArchive
} from "@fortawesome/free-solid-svg-icons";
const RemoveIcon = styled.span`
    cursor:pointer;
  width: 20px;
`;
const StyledTodoList=styled.div`
width:900px;
margin-left:auto;
margin-right:auto;
`;
const StyledTodo = styled.div`
  height: auto;
  margin: 15px;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid blue;
  text-align:left;
  width: 800px
`;
const StyledTodoTitle = styled.span`
  font-size: 24px;
  font-weight: 600;
`;
const StyledAction=styled.span`
margin-left:auto;
float: right;
color: red;
`
const StyledTags = styled.span`
`
const StyledTag = styled.span<{color?:string}>`
  margin-left: 8px;
  padding: 6px;
  border-radius: 4px;
  
  background-color:${props => props.color ? props.color : "green"};
  color:white;
`;

export function TodoRow({data}:{data:TodoWithTagDetails}){
    const dispatch = useAppDispatch();
    return (
        <StyledTodo>
            <StyledTodoTitle>{data.name}</StyledTodoTitle>
            <StyledTags>
            {data.tags.map((tag)=>
            <StyledTag key = {tag.id} color={tag.name=="Data Unavailable"?'red':'green'}>
                {tag.name}
            </StyledTag>)}
            </StyledTags>
            <StyledAction>
                    <RemoveIcon>
                        <FontAwesomeIcon
                        icon={faArchive}
                        fontSize="12px"
                        onClick={()=>{dispatch(archiveAsync(data.id))}}
                    />
                    </RemoveIcon>
                    </StyledAction>
        </StyledTodo>
        
    )
}
export function TodoList(){
    const todos = useAppSelector(selectTodos);
    const dispatch = useAppDispatch();
    useEffect(()=>{
        dispatch(refreshAsync(todos))
    },[])
    return (
        <div>
            <button onClick={()=>{
                dispatch(refreshTags())
                dispatch(refreshAsync())
            }}>Refresh</button>
            <StyledTodoList>
            {todos?.filter((todo)=>todo.status==TodoStatus.Active).map(todo=><TodoRow key = {todo.id} data={todo}/>)}
        </StyledTodoList>
        </div>
        
    )
}