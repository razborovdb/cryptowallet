import * as React from 'react';
import styled from "styled-components";
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { PrimaryButton } from "./CommonStyled";
import { cryptosUpdate } from "../slices/CryptoCurrenciesSlice";

export default function EditCryptoTemplate({cryptoName}) {
    const [open, setOpen] = React.useState(false);

    const dispatch = useDispatch();
    const {cryptos} = useSelector((state) => state.cryptos);

    const auth = useSelector((state) => state.auth);

    const [currentCrypto, setCurrentCrypto] = useState({});
    const [previewImg, setPreviewImg] = useState("");
    const { updateStatus } = useSelector((state) => state.cryptos);

    const [cryptoImg, setCryptoImg] = useState("");

    const [cryptoImgUrl, setCryptoImgUrl] = useState("");
    const [name, setName] = useState("");
    const [desc, setDesc] = useState("");
    const [amount, setAmount] = useState(0.0);
    const [cost, setCost] = useState(0.0);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {

    e.preventDefault();

    dispatch(
      cryptosUpdate({
        name,
        image: cryptoImg,
        imageUrl: cryptoImgUrl,
        desc,
        amount,
        cost,
        token: auth.token,
      })
      
    );

    //setOpen(false);
  };

  const handleClickOpen = () => {
    setOpen(true);
    let selectedCrypto = cryptos.filter((crypto) => crypto.cryptoName === cryptoName);
    selectedCrypto = selectedCrypto[0];


    setCurrentCrypto(selectedCrypto);
    setPreviewImg(selectedCrypto.imageUrl);
    setName(selectedCrypto.cryptoName);
    setDesc(selectedCrypto.cryptoDescription);
    setAmount(selectedCrypto.cryptoAmount);
    setCost(selectedCrypto.cryptoCost);



  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Edit onClick={handleClickOpen}>
        Edit
      </Edit>
      <Dialog open={open} onClose={handleClose} fullWidth={false} maxWidth={"md"}>
        <DialogTitle>Edit Crypto</DialogTitle>
        <DialogContent>
            <StyledEditCrypto>
                <StyledForm onSubmit={handleSubmit}>
                    <h3>Edit a Crypto: {name}</h3>
                    <input
                    type="text"
                    placeholder="Description"
                    onChange={(e) => setDesc(e.target.value)}
                    value={desc}
                    required
                    />
                    <input
                    type="number"
                    step="0.000001"
                    placeholder="Amount"
                    onChange={(e) => setAmount(e.target.value)}
                    value={amount}
                    required
                    />
                    <input
                    type="number"
                    step="0.01"
                    placeholder="Cost"
                    onChange={(e) => setCost(e.target.value)}
                    value={cost}
                    required
                    />

                    <PrimaryButton type="submit">
                    {updateStatus === "pending" ? "Submitting" : "Submit"}
                    </PrimaryButton>
                    <div className="back-to-cryptos">
                      <Button onClick={handleClose}>
                        <svg xmlns="http://www.w3.org/2000/svg" 
                          width="20" 
                          height="20" 
                          fill="currentColor" 
                          className="bi bi-arrow-left" 
                          viewBox="0 0 16 16">
                          <path fillRule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                        </svg>
                        <span>Back To Cryptos</span>
                        
                      </Button>
                    </div>
                </StyledForm>
  
            </StyledEditCrypto>
        </DialogContent>
        
      </Dialog>
    </div>
  );
}

const Edit = styled.div`
  border: none;
  outline: none;
  padding: 3px 5px;
  color: white;
  border-radius: 3px;
  cursor: pointer;
  background-color: #4b70e2;
`;

const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  max-width: 300px;
  margin-top: 2rem;
  select,
  input {
    padding: 7px;
    min-height: 30px;
    outline: none;
    border-radius: 5px;
    border: 1px solid rgb(182, 182, 182);
    margin: 0.3rem 0;
    &:focus {
      border: 2px solid rgb(0, 208, 255);
    }
  }
  select {
    color: rgb(95, 95, 95);
  }
`;

const StyledEditCrypto = styled.div`
  display: flex;
  justify-content: space-between;
`;