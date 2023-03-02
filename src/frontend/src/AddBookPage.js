import {Component} from "react";
import axios from "axios";

class AddBookPage extends Component{
    constructor(props) {
        super(props);
        this.state={
            title:'',
            author:'',
            isbn:'',
            availableCopies:null
        };
        this.onChange=this.onChange.bind(this);
        this.kaydet=this.kaydet.bind(this);
        this.setBook=this.setBook.bind(this);
        this.isValidBook=this.isValidBook.bind(this);
    }
    onChange(e){
        this.setState({[e.target.id]:e.target.value});
    }

    setBook(){
        let book = {};
        book.title = this.state.title;
        book.author = this.state.author;
        book.isbn = this.state.isbn;
        book.availableCopies = this.state.availableCopies;
        return book;
    }

    isValidBook(){
        let valid = false;
        let title = this.state.title;
        let author = this.state.author;
        let isbn = this.state.isbn;
        let availableCopies = this.state.availableCopies;
        if ((title!=="" && title!==null)&&(author!=="" && author!==null)&&(isbn!=="" && isbn!==null)&&availableCopies!==null && availableCopies>=0){
            valid = true;
        }
        return valid;
    }

    kaydet(){
        let valid = this.isValidBook();
        if (valid){
            let book = this.setBook();
            axios.post("http://localhost:8080/api/book/addBook",book).then(response => {
                console.log(response);
            })
        }
    }

    render() {
        return(
            <div>
                <div>
                    <h1>Kitap Ekleme Sayfası</h1>
                </div>
                <div>
                    <p>Kitap İsmi</p>
                    <input
                    name="title"
                    id="title"
                    value={this.state.title}
                    type="text"
                    onChange={this.onChange}
                    />
                </div>
                <div>
                    <p>Yazar İsmi</p>
                    <input
                        name="author"
                        id="author"
                        value={this.state.author}
                        type="text"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <p>ISBN</p>
                    <input
                        name="isbn"
                        id="isbn"
                        value={this.state.isbn}
                        type="text"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <p>Kitap İsmi</p>
                    <input
                        name="availableCopies"
                        id="availableCopies"
                        value={this.state.availableCopies}
                        type="number"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <button
                        className="saveBook btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px',marginTop:'10px'}}
                        onClick={this.kaydet}>Kitabı Kaydet>
                    </button>
                </div>
            </div>
        )
    }
}
export default AddBookPage;