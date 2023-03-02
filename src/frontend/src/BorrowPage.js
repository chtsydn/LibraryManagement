import {Component} from "react";
import axios from "axios";

class BorrowPage extends Component{
    constructor(props) {
        super(props);
        this.state={
            isbn:'',
            email:''
        };
        this.onChange=this.onChange.bind(this);
        this.kaydet=this.kaydet.bind(this);
        this.isValidBorrow=this.isValidBorrow.bind(this);
    }
    onChange(e){
        this.setState({[e.target.id]:e.target.value});
    }

    isValidBorrow(){
        let valid = false;
        let isbn = this.state.isbn;
        let email = this.state.email;
        if ((isbn!=="" && isbn!==null)&&(email!=="" && email!==null)){
            valid = true;
        }
        return valid;
    }

    kaydet(){
        let valid = this.isValidBorrow();
        if (valid){
            axios.get("http://localhost:8080/api/borrow/borrowBook",{params:{bookIsbn:this.state.isbn,borrowerEmail:this.state.email}}).then(response => {
                console.log(response);
            })
        }
    }

    render() {
        return(
            <div>
                <div>
                    <h1>Kitap Ödünç Verme Sayfası</h1>
                </div>
                <div>
                    <p>Kitap ISBN</p>
                    <input
                        name="isbn"
                        id="isbn"
                        value={this.state.isbn}
                        type="text"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <p>Kullanıcı Email</p>
                    <input
                        name="email"
                        id="email"
                        value={this.state.email}
                        type="text"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <button
                        className="saveBorrow btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px',marginTop:'10px'}}
                        onClick={this.kaydet}>Kitabı Ödünç Ver>
                    </button>
                </div>
            </div>
        )
    }
}
export default BorrowPage;