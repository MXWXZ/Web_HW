import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Divider, Table, Input, Row, message, Modal } from 'antd'
import { Form, Upload, Icon, Col, InputNumber } from 'antd';
import Qs from 'qs';
import axios from 'axios';

const Search = Input.Search;
const confirm = Modal.confirm;
const { TextArea } = Input;

function beforeUpload(file) {
    const checkType = file.type === 'image/jpeg' || file.type === 'image/png';
    if (!checkType)
        message.error('You can only upload JPG/PNG file!');
    const checkSize = file.size / 1024 / 1024 < 2;
    if (!checkSize)
        message.error('Image must smaller than 2MB!');
    return checkType && checkSize;
}

class EditBook extends Component {
    state = {
        loading: false,
        uploading: false,
        bookImg: '',
    }

    componentDidMount() {
        if (this.props.bookId !== '')
            axios.get(`/api/books`, {
                params: {
                    bookId: this.props.bookId
                }
            })
                .then(res => {
                    const data = res.data.data;
                    this.props.form.setFieldsValue({
                        bookId: data.bookId,
                        bookName: data.bookName,
                        bookAuthor: data.bookAuthor,
                        bookPrice: (data.bookPrice / 100).toFixed(2),
                        bookIsbn: data.bookIsbn,
                        bookAmount: data.bookAmount,
                        bookDetail: data.bookDetail
                    })
                    this.setState({ bookImg: data.bookImg })
                });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({ loading: true });
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let newValue = values;
                newValue.bookPrice = parseInt(newValue.bookPrice * 100);
                newValue.bookImg = this.state.bookImg;
                if (this.props.bookId !== '') {
                    axios.put('/api/books', Qs.stringify(values), { headers: { token: sessionStorage.getItem('token') } })
                        .then(response => {
                            this.setState({ loading: false });

                            let data = response.data;
                            if (data.code !== 0) {
                                message.error(data.msg);
                            } else {
                                message.success('Book update success!');
                                setTimeout(() => { window.location.reload(); }, 1000);
                            }
                        })
                } else {
                    axios.post('/api/books', Qs.stringify(values), { headers: { token: sessionStorage.getItem('token') } })
                        .then(response => {
                            this.setState({ loading: false });

                            let data = response.data;
                            if (data.code !== 0) {
                                message.error(data.msg);
                            } else {
                                message.success('Book add success!');
                                setTimeout(() => { window.location.reload(); }, 1000);
                            }
                        })
                }
            }
            this.setState({ loading: false });
        });
    }

    handleChange = info => {
        if (info.file.status === 'uploading') {
            this.setState({ uploading: true });
            return;
        }
        if (info.file.status === 'done') {
            const data = info.file.response;
            if (data.code !== 0) {
                message.error(data.msg);
            } else {
                this.setState({
                    loading: false,
                    bookImg: data.data
                });
            }
        }
    };

    validatePrice = (rule, value, callback) => {
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if (value === '' || reg.test(value)) {
            callback();
        } else {
            callback('Please input correct price!');
        }
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        const uploadButton = (
            <div>
                <Icon type={this.state.loading ? 'loading' : 'plus'} />
                <div className='ant-upload-text'>Upload</div>
            </div>
        );
        return (
            <Form onSubmit={this.handleSubmit} labelAlign='left' labelCol={{ span: 6 }} wrapperCol={{ span: 18 }}>
                <Row>
                    <Col span={7}>
                        <Upload name='bookImg' listType='picture-card' showUploadList={false}
                            action='/api/images'
                            headers={{ token: sessionStorage.getItem('token') }}
                            beforeUpload={beforeUpload} onChange={this.handleChange}>
                            {this.state.bookImg ? <img className='book-img' src={'/image/' + this.state.bookImg} alt='avatar' /> : uploadButton}
                        </Upload>
                    </Col>
                    <Col span={17}>
                        <Form.Item label="ID" className='book-form' style={{ display: 'none' }}>
                            {getFieldDecorator('bookId', { initialValue: this.props.bookId })(
                                <Input />,
                            )}
                        </Form.Item>
                        <Form.Item label="Name" className='book-form'>
                            {getFieldDecorator('bookName', {
                                rules: [{ required: true, message: 'Please input book name!' }],
                            })(
                                <Input />,
                            )}
                        </Form.Item>
                        <Form.Item label="Author" className='book-form'>
                            {getFieldDecorator('bookAuthor', {
                                rules: [{ required: true, message: 'Please input author!' }],
                            })(
                                <Input />,
                            )}
                        </Form.Item>
                        <Form.Item label="Price" className='book-form'>
                            {getFieldDecorator('bookPrice', {
                                rules: [
                                    { validator: this.validatePrice },
                                    {
                                        required: true,
                                        message: 'Please input price!'
                                    }],
                            })(
                                <Input id="bookPrice" />,
                            )}
                        </Form.Item>
                        <Form.Item label="ISBN" className='book-form'>
                            {getFieldDecorator('bookIsbn', {
                                rules: [{ required: true, message: 'Please input ISBN!' }],
                            })(
                                <Input />,
                            )}
                        </Form.Item>
                        <Form.Item label="Amount">
                            {getFieldDecorator('bookAmount', {
                                initialValue: 0,
                                rules: [
                                    { type: 'integer' },
                                    { required: true, message: 'Please input amount!' }],
                            })(
                                <InputNumber min={0} max={9999} />,
                            )}
                        </Form.Item>
                        <Form.Item label="Detail">
                            {getFieldDecorator('bookDetail', {
                                rules: [{ required: true, message: 'Please input detail!' }],
                            })(
                                <TextArea rows={6} />,
                            )}
                        </Form.Item>
                    </Col>
                </Row>
                <Row>
                    <Col offset={6} span={12}>
                        <Button type='primary' htmlType='submit' loading={this.state.loading} className='login-form-button'>Save</Button>
                    </Col>
                </Row>
            </Form >
        );
    }
}

class BookView extends Component {
    state = {
        book: [],
        bookSave: [],
        visible: false,
        selectedId: '',
    }

    showModal = (e) => {
        this.setState({
            selectedId: e.target.name,
            visible: true
        });
    }

    handleCancel = () => {
        this.setState({ visible: false });
    }

    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.deleteBook = this.deleteBook.bind(this);
    }

    componentDidMount() {
        axios.get(`/api/books`)
            .then(res => {
                this.setState({
                    book: res.data.data,
                    bookSave: res.data.data
                })
            });
    }

    deleteBook(e) {
        let id = parseInt(e.target.name);
        let call = this;
        confirm({
            title: 'Are you sure to delete this book?',
            content: 'This operation can not be undo!',
            okText: 'Yes',
            okType: 'danger',
            cancelText: 'No',
            onOk() {
                axios.delete(`/api/books`, {
                    params: {
                        bookId: id
                    },
                    headers: {
                        token: sessionStorage.getItem('token')
                    }
                })
                    .then(res => {
                        if (res.data.code !== 0) {
                            message.error(res.data.msg);
                        } else {
                            let newbook = call.state.book;
                            for (let i = 0, len = newbook.length; i < len; ++i) {
                                if (newbook[i].bookId === id) {
                                    newbook.splice(i, 1);
                                    break;
                                }
                            }
                            call.setState({
                                book: newbook,
                                bookSave: newbook
                            })
                            message.success('Book deleted!');
                        }
                    });
            }
        });
    }

    handleChange() {
        let pattern = document.getElementById('search').value;
        let list = this.state.bookSave.filter((item) => {
            return item.bookName.indexOf(pattern) !== -1;
        })
        this.setState({
            book: list
        })
    }

    render() {
        const columns = [
            {
                title: 'Image',
                dataIndex: 'bookImg',
                align: 'center',
                width: 150,
                render: bookImg => (
                    <img className='book-img' src={'/image/' + bookImg} alt={bookImg} />
                )
            }, {
                title: 'Name',
                dataIndex: 'bookName',
                align: 'center',
                sorter: (a, b) => a.bookName.localeCompare(b.bookName),
                render: (text, record) => (<Link className='book-title' to={'/book/' + record.bookId.toString()}>{text}</Link>)
            }, {
                title: 'Author',
                dataIndex: 'bookAuthor',
                align: 'center',
                sorter: (a, b) => a.bookAuthor.localeCompare(b.bookAuthor),
            }, {
                title: 'ISBN',
                dataIndex: 'bookIsbn',
                align: 'center',
                sorter: (a, b) => a.bookIsbn.localeCompare(b.bookIsbn),
            }, {
                title: 'Amount',
                dataIndex: 'bookAmount',
                align: 'center',
                sorter: (a, b) => a.bookAmount - b.bookAmount,
            }, {
                title: 'Price',
                dataIndex: 'bookPrice',
                align: 'center',
                sorter: (a, b) => a.bookPrice - b.bookPrice,
                render: bookPrice => ((bookPrice / 100).toFixed(2))
            }
        ];
        let EditForm;
        if (this.props.showAction !== undefined) {
            const WrappedBookForm = Form.create({ name: 'edit_book' })(EditBook);
            columns.push({
                title: 'Action',
                align: 'center',
                render: (text, record) => (
                    <span>
                        <Button name={record.bookId} size='small' onClick={this.showModal}>Edit</Button>
                        <Divider type='vertical' />
                        <Button name={record.bookId} size='small' type='danger' onClick={this.deleteBook}>Delete</Button>
                    </span>
                )
            });

            EditForm = (
                <div style={{ display: 'inline' }}>
                    <Button type='primary' style={{ float: 'right' }} onClick={this.showModal}>Add new book</Button>
                    <Modal visible={this.state.visible} title='Book info' onCancel={this.handleCancel} footer={null}>
                        <WrappedBookForm bookId={this.state.selectedId} />
                    </Modal>
                </div>);
        }
        return (
            <div>
                <Row style={{ paddingBottom: '20px' }}>
                    <Search id='search' placeholder='search book' onChange={this.handleChange} style={{ width: 300 }} />
                    {EditForm}
                </Row>
                <Row>
                    <Table rowKey={record => record.bookId} bordered={true} columns={columns} dataSource={this.state.book} />
                </Row>
            </div>
        );
    }
}

export default BookView;