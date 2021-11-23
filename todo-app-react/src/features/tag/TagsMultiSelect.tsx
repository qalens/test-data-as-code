import React, { useEffect, useRef, useState } from "react";
import styled from "@emotion/styled";
import CreatableSelect from "react-select/creatable";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {addAsync, refreshAsync, selectNewTag, selectTags, TagItem} from "./tagSlice";
import {useAppDispatch, useAppSelector} from "../../app/hooks";


type MultiSelectProps = {
    onChange:(items:number[])=>void,
};


export const TagsMultiSelect: React.FC<MultiSelectProps> = ({onChange}:MultiSelectProps) => {
    const dispatch = useAppDispatch()
    const newlyAddedTag = useAppSelector(selectNewTag);
    const items = useAppSelector(selectTags)

    const [selectedOption, setSelectedOption] = useState<Readonly<{label:string,value:number}[]>>([]);
    // const options = items.map(item=>{return {value:item.id.toString(),label : item.name}})
      
    useEffect(()=>{
        dispatch(refreshAsync())
    },[])
    useEffect(()=>{
        if(newlyAddedTag)
            setSelectedOption([...selectedOption,{label:newlyAddedTag.name,value:newlyAddedTag.id}])
    },[newlyAddedTag])
    let [options,setOptions] = useState(items.map(item=>{
        return { value: item.id, label: item.name }       
    }));
    useEffect(()=>{
        setOptions(items.map(item=>{
            return { value: item.id, label: item.name }       
        }))
    },[items])
    useEffect(()=>{
        onChange(selectedOption.map(op=>op.value))
    },[selectedOption])
    return (
        <div>
            <CreatableSelect
            isMulti={true}
        value={selectedOption}
        isClearable
        onChange={(value)=>{
            setSelectedOption(value)
        }}
        onCreateOption={
            (value)=>{
                dispatch(addAsync(value))
            }
        }
        options={options}
      />
        </div>
    );
};
