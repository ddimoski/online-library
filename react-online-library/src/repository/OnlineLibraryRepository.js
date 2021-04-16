import axios from '../custom.axios/axios';

const OnlineBookingRepository = {
    fetchBook: (id) => {
        return axios.get(`/book/${id}`);
    },

    fetchAllBooks: () => {
        return axios.get(`/book/all`);
    },

    createBook: (name, authorName, authorSurname, authorCountry, category) => {
        return axios.post("/book/create", {
            name: name,
            author: {
                name: authorName,
                surname: authorSurname,
                country: authorCountry
            },
            category: category
        })
    },

    editBook: (id, name, authorName, authorSurname, authorCountry, category) => {
        return axios.post(`/book/${id}/edit`, {
            name: name,
            author: {
                name: authorName,
                surname: authorSurname,
                country: authorCountry
            },
            category: category
        })
    },

    deleteBook: (id) => {
        return axios.delete(`/book/${id}/delete`)
    }
}

export default OnlineBookingRepository;