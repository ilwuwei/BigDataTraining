/*
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-21 00:26:59
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 01:45:22
 * @FilePath: \bookmangement\src\network\api.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 
import request  from "./request"

// bookList
export const bookList = ()=>{
    return request.get("/bookList")
}

// 图书路由
export const categoryTime = ()=>{
    return request.get("/categoryTime")
};
export const bookTime = ()=>{
    return request.get("/bookTime")
};
export const bookPrice = ()=>{
    return request.get("/bookPrice")
};
export const bookCategory = ()=>{
    return request.get("/bookCategory")
};

// 作者路由
export const bookComment = () =>{
    return request.get("/bookComment")
};
export const bookAuthor = () =>{
    return request.get('/bookAuthor')
}
export const authorTop10 = ()=>{
    return request.get('/authorTop10')
}

// 评分路由
export const bookPublisher = () =>{
    return request.get("/bookPublisher")
}
export const press = () =>{
    return request.get("/bookPublisher")
}
export const bookScore = ()=>{
    return request.get("/bookScore")
}
export const bookCom = () =>{
    return request.get("/bookComment")
}