import React, {useEffect, useState} from 'react'
import {useDispatch} from 'react-redux'
import {diaryCreate, diaryShow} from '../../_actions/diaryAction'

const DiaryPage = () => {
    const dispatch = useDispatch()
    const [text, setText] = useState('')
    const [diarys, setDiarys] = useState([])

    const handleInputChange = e => {
        setText(e.target.value)
    }

    const hadleSubmit = e => {
        e.preventDefault()
        let formData = new FormData()

        let files = e.target.image.files
        formData.append('image', files[0])
        formData.append('content', text)

        // FormData의 key 확인
        for (let key of formData.keys()) {
            console.log(key)
        }

        // FormData의 value 확인
        for (let value of formData.values()) {
            console.log(value)
        }

        dispatch(diaryCreate(formData)).then(res => {
            console.log(res)
        })
    }

    useEffect(() => {
        dispatch(diaryShow()).then(res => {
            console.log(res.payload)
            setDiarys(res.payload)
        })
    }, [])

    const RenderDiarys =
        diarys &&
        diarys.map((diary, index) => {
            console.log(diary)
            return (
                <li key={index}>
                    <div>
                        <img src={diary.imagePath} alt="img" />
                    </div>
                    <div>{diary.content}</div>
                </li>
            )
        })

    return (
        <div>
            <form onSubmit={hadleSubmit}>
                <label htmlFor="">한줄일기</label>
                <input type="text" name="text" value={text} onChange={handleInputChange} />
                <input type="file" name="image" />
                <button type="submit">쓰기</button>
            </form>
            <ul>{RenderDiarys}</ul>
        </div>
    )
}

export default DiaryPage
