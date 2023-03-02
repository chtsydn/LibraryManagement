import {Component} from "react";
import axios from "axios";

class AddBorrowerPage extends Component{
    constructor(props) {
        super(props);
        this.state={
            name:'',
            email:'',
            phoneNumber:''
        };
        this.onChange=this.onChange.bind(this);
        this.kaydet=this.kaydet.bind(this);
        this.setBorrower=this.setBorrower.bind(this);
        this.isValidBorrower=this.isValidBorrower.bind(this);
    }
    onChange(e){
        this.setState({[e.target.id]:e.target.value});
    }

    setBorrower(){
        let borrower = {};
        borrower.name = this.state.name;
        borrower.email = this.state.email;
        borrower.phoneNumber = this.state.phoneNumber;
        borrower.bookIsbn = "";
        return borrower;
    }

    isValidBorrower(){
        let valid = false;
        let name = this.state.name;
        let email = this.state.email;
        let phoneNumber = this.state.phoneNumber;
        if ((name!=="" && name!==null)&&(email!=="" && email!==null)&&(phoneNumber!=="" && phoneNumber!==null)){
            valid = true;
        }
        return valid;
    }

    kaydet(){
        let valid = this.isValidBorrower();
        if (valid){
            let borrower = this.setBorrower();
            axios.post("http://localhost:8080/api/borrower/addBorrower",borrower).then(response => {
                console.log(response);
            })
        }
    }

    render() {
        return(
            <div>
                <div>
                    <h1>Kullanıcı Ekleme Sayfası</h1>
                </div>
                <div>
                    <p>Kullanıcı İsmi</p>
                    <input
                        name="name"
                        id="name"
                        value={this.state.name}
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
                    <p>Kullanıcı Telefon</p>
                    <input
                        name="phoneNumber"
                        id="phoneNumber"
                        value={this.state.phoneNumber}
                        type="text"
                        onChange={this.onChange}
                    />
                </div>
                <div>
                    <button
                        className="saveBorrower btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px',marginTop:'10px'}}
                        onClick={this.kaydet}>Kullanıcıyı Kaydet>
                    </button>
                </div>
            </div>
        )
    }
}
export default AddBorrowerPage;