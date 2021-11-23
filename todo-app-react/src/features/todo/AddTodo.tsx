import {addAsync, TodoStatus} from "./todoSlice";
import React, {useState} from "react";
import {useAppDispatch} from "../../app/hooks";
import {TagsMultiSelect} from "../tag/TagsMultiSelect";
import { TagItem } from "../tag/tagSlice";
import styled from "@emotion/styled";
const StyledMultiSelect = styled.span`
display: inline-block;
width:300px;
margin:10px;
`
const StyledTodoName = styled.input`
display: inline-block;
font-size: 26px;
width:400px;
margin:10px;
`
const StyledAddButton = styled.button`
display: inline-block;
font-size: 26px;
width:80px;
margin:10px;
`
export function AddTodo() {
    const [name, setName] = useState("New Todo")
    const [tags,setTags] = useState<number[]>([])
    const dispatch = useAppDispatch();
    return (<div>
        <StyledTodoName name={"name"} value={name} onChange={(e) => setName(e.target.value)}/>
        <StyledMultiSelect>
            <TagsMultiSelect onChange={(selectedTags)=>{
            setTags(selectedTags)
        }}/>
        </StyledMultiSelect>
        
        <StyledAddButton onClick={() => dispatch(addAsync({name: name, status: TodoStatus.Active, tags}))}>Add</StyledAddButton>
    </div>)
}
