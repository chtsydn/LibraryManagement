import React, {Component} from "react";
import AddBookPage from "./AddBookPage";
import AddBorrowerPage from "./AddBorrowerPage";
import BorrowPage from "./BorrowPage";
import ReturnPage from "./ReturnPage";

class MainMenu extends Component{

    constructor(props) {
        super(props);
        this.state = {
            bookPage:false,
            borrowerPage:false,
            borrowPage:false,
            returnPage:false
        };
        this.addBookPage = this.addBookPage.bind(this);
        this.addBorrowerPage = this.addBorrowerPage.bind(this);
        this.borrowBookPage = this.borrowBookPage.bind(this);
        this.returnBookPage = this.returnBookPage.bind(this);
    }
    addBookPage(){
        this.setState({
            bookPage:true,
            borrowerPage:false,
            borrowPage:false,
            returnPage:false
        });
    }
    addBorrowerPage(){
        this.setState({
            bookPage:false,
            borrowerPage:true,
            borrowPage:false,
            returnPage:false
        });
    }
    borrowBookPage(){
        this.setState({
            bookPage:false,
            borrowerPage:false,
            borrowPage:true,
            returnPage:false
        });
    }
    returnBookPage(){
        this.setState({
            bookPage:false,
            borrowerPage:false,
            borrowPage:false,
            returnPage:true
        });
    }

    render() {
        return(
            <div>
                <div>
                    <button
                        className="addBook btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px'}}
                        onClick={this.addBookPage}>Kitap Ekle
                    </button>
                    <button
                        className="addBorrower btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px'}}
                        onClick={this.addBorrowerPage}>Kullanıcı Ekle
                    </button>
                    <button
                        className="borrowBook btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px'}}
                        onClick={this.borrowBookPage}>Kitap Ödünç Ver
                    </button>
                    <button
                        className="returnBook btn"
                        type="button"
                        style={{padding:'10px 10px 10px 10px'}}
                        onClick={this.returnBookPage}>Kitap Geri Al
                    </button>
                </div>
                {this.state.bookPage &&
                    <AddBookPage></AddBookPage>
                }
                {this.state.borrowerPage &&
                    <AddBorrowerPage></AddBorrowerPage>
                }
                {this.state.borrowPage &&
                    <BorrowPage></BorrowPage>
                }
                {this.state.returnPage &&
                    <ReturnPage></ReturnPage>
                }
            </div>
        )
    }
}
export default MainMenu;