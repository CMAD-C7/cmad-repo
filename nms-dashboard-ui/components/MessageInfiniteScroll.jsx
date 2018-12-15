import React from "react";
import Table from "./Table.jsx";
import store from "../stores/store.js";
import "../style/custom.css";

class MessageInfiniteScroll extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            requestSent: false,
            page: 0,
            lastPage: false
        };
    }

    componentDidMount() {

        this.loadLatestMessages();
        this.interval = setInterval(() => this.loadLatestMessages(), this.props.refreshinterval);
        this.refs.iScrollTable.addEventListener("scroll", () => {
            if (
                this.refs.iScrollTable.scrollTop + this.refs.iScrollTable.clientHeight >=
                this.refs.iScrollTable.scrollHeight
            ) {
                this.loadMoreMessages();
            }
        });
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    componentDidUpdate(prevProps) {
        if (prevProps.refreshinterval !== this.props.refreshinterval) {
            clearInterval(this.interval);
            if (this.props.refreshinterval > 0) {
                this.interval = setInterval(() => this.loadLatestMessages(), this.props.refreshinterval);
            }
        }
    }

    loadLatestMessages(size = 10, sort = "dateAdded,desc") {

        if (this.state.requestSent) {
            return;
        }
        // Resting state
        this.setState({
            messages: [],
            requestSent: true,
            page: 0,
            lastPage: false
        });
       var reqURL = "messages?page=" + this.state.page + "&size=" + size + "&sort=" + sort;
       // var reqURL = "/data/messages.json";
        console.log("loadLatestMessages=>" + reqURL);
        fetch(reqURL)
            .then(response => response.json())
            .then(response => {
                this.setState({ messages: response.content })
                this.setState({ requestSent: false, page: this.state.page + 1, lastPage: response.last })
            }).catch(err => {
                console.error(err)
            })
    }


    loadMoreMessages(page = 0, size = 10, sort = "dateAdded,desc") {

        if (this.state.requestSent) {
            return;
        }
        this.setState({ requestSent: true });
        var reqURL = "messages?page=" + this.state.page + "&size=" + size + "&sort=" + sort;
        console.log("loadMoreMessages=>" + reqURL);
        fetch(reqURL)
            .then(response => response.json())
            .then(response => {
                var messages = this.state.messages.concat(response.content);
                this.setState({ messages: messages })
                this.setState({ requestSent: false, page: this.state.page + 1, lastPage: response.last })
            }).catch(err => {
                console.error(err)
                this.setState({ requestSent: false })
            })
    }


    render() {
        return (
            <div className="wrap">
                <table className="head">
                    <tbody>
                        <tr>
                            <td width="50px" >Id</td>
                            <td width="120px" >Message Type</td>
                            <td width="200px">Source</td>
                            <td>Description</td>
                            <td width="220px">Date Added</td>
                        </tr>
                    </tbody>
                </table>
                <div ref="iScrollTable" className="inner_table">
                    <table>
                        <tbody>
                            {this.state.messages.map(message =>
                                <tr key={message.id}>
                                    <td width="50px">{message.id}</td>
                                    <td width="120px">{message.type}</td>
                                    <td width="200px">{message.source}</td>
                                    <td>{message.description}</td>
                                    <td width="220px">{message.dateAdded}</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
                {this.state.requestSent ? <p className="loading"> loading More Messages...</p> : ""}
            </div>
        );
    }
}

export default MessageInfiniteScroll;