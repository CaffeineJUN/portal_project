import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import {diaryCreate} from '../../_actions/albumAction'

const DiaryPage = () => {
    const dispatch = useDispatch()
    const [text, setText] = useState('')

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

    return (
        <div>
            <form onSubmit={hadleSubmit}>
                <label htmlFor="">한줄일기</label>
                <input type="text" name="text" value={text} onChange={handleInputChange} />
                <input type="file" name="image" />
                <button type="submit">쓰기</button>
            </form>
        </div>
    )
}

export default DiaryPage
