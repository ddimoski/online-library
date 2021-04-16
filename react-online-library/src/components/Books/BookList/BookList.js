import React from 'react';
import {Link} from 'react-router-dom';
import {BookTerm} from '../BookTerm/BookTerm'

class BookList extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        const books = this.fetchBooks();

        return (
          <div className={"container mm-4 mt-5"}>
              <div className={"row"}>
                  <div className={"table-responsive"}>
                      <table className={"table table-striped"}>
                          <thead>
                          <tr>
                              <th scope={"col"}>Name</th>
                              <th scope={"col"}>Author</th>
                              <th scope={"col"}>Category</th>
                              <th scope={"col"}>Available copies</th>
                          </tr>
                          </thead>
                          <tbody>
                          {books}
                          </tbody>
                      </table>
                  </div>
                  <div className="col mb-3">
                      <div className="row">
                          <div className="col-sm-12 col-md-12">
                              <Link className={"btn btn-block btn-dark"} to={"/book/add"}>Add a new book</Link>
                          </div>
                      </div>
                  </div>
              </div>

              {/*Add react pagination*/}
          </div>


              )
    }

    fetchBooks = () => {
        return (
          <BookTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}/>
        );
    }

}