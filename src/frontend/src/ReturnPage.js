import {Component} from "react";
import axios from "axios";

class ReturnPage extends Component{
    constructor(props) {
        super(props);
        this.state={
            email:''
        };
        this.onChange=this.onChange.bind(this);
        this.kaydet=this.kaydet.bind(this);
        this.isValidReturn=this.isValidReturn.bind(this);
    }
    onChange(e){
        this.setState({[e.target.id]:e.target.value});
    }

    isValidReturn(){
        let valid = false;
        let email = this.state.email;
        if ((email!=="" && email!==null)){
            valid = true;
        }
        return valid;
    }

    kaydet(){
        let valid = this.isValidReturn();
        if (valid){
            axios.get("http://localhost:8080/api/borrow/returnBook",{params:{borrowerEmail:this.state.email}}).then(response => {
                console.log(response);
            })
        }
    }

    render() {
        return(
            <div>
                <div>
                    <h1>Kitap Geri Alma Sayfası</h1>
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
                        className="saveRaturn btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px',marginTop:'10px'}}
                        onClick={this.kaydet}>Kitabı Geri Al>
                    </button>
                </div>
            </div>
        )
    }
}
export default ReturnPage;