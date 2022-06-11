import React, {useEffect, useState} from 'react'
import {useDispatch} from 'react-redux'
import {diaryCreate, diaryShow} from '../../_actions/diaryAction'
import {FcCancel} from 'react-icons/fc'

import styles from './DiaryPage.module.css'
import axios from 'axios'

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
        // FormData의 value 확인-
        for (let value of formData.values()) {
            console.log(value)
        }

        dispatch(diaryCreate(formData)).then(res => {
            console.log(res)
            window.location.replace('/diary')
        })
    }

    const deleteDiary = id => {
        axios.delete(`/api/user/diary/${id}`).then(res => {
            console.log(res)
            window.location.replace('/diary')
        })
    }

    useEffect(() => {
        dispatch(diaryShow()).then(res => {
            setDiarys(res.payload)
        })
    }, [])

    const RenderDiarys =
        diarys &&
        diarys.map((diary, index) => {
            const imgroot = `${diary.imagePath.substr(55)}`
            return (
                <li
                    key={index}
                    onMouseEnter={e => {
                        e.target.children[2].className = `${styles.showBtn}`
                    }}
                    onMouseLeave={e => {
                        e.target.children[2].className = ``
                    }}
                >
                    <div>
                        <img src={imgroot} alt="img" />
                    </div>
                    <div>{diary.content}</div>
                    <button
                        onClick={() => {
                            deleteDiary(diary.id)
                        }}
                    >
                        <FcCancel />
                    </button>
                </li>
            )
        })

    return (
        <div className={styles.container}>
            <div>
                <a href="/">Home</a>
                <a href="/updateUser">Update userinfo</a>
            </div>
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
