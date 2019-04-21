import React, { Component } from 'react';
import BookView from './components/BookView';

class BookManager extends Component {
    render() {
        return (
            <BookView showAction />
        );
    }
}

export default BookManager;