import React from "react";
import TableComponent from "./TableComponent.jsx";
import store from "../stores/store.js";

class MessageCountTable extends React.Component {
    constructor(props) {
        super(props);
        store.subscribe(()=>{
            this.forceUpdate();
        });
    }

    render() {
        const tableColumns = ["Error","Warning","Notification"];
        const tableData = store.getState().messagecount;
        return (
            <div><TableComponent columns={tableColumns} data={tableData}></TableComponent></div>
        );
    }
}

export default MessageCountTable;