package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/23/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddressBookFragment extends Fragment {
    private RecyclerView mAddressBookRecyclerView;
    private ContactAdapter mContactAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_book, container,
                false);
        mAddressBookRecyclerView =
                (RecyclerView) view.findViewById(R.id.address_book_recycler_view);
        mAddressBookRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        AddressBook addressBook = AddressBook.get();
        List<Contact> contacts = addressBook.getContacts();
        if (mContactAdapter == null) {
            mContactAdapter = new ContactAdapter(contacts);
            mAddressBookRecyclerView.setAdapter(mContactAdapter);
        }
        else {
            mContactAdapter.notifyDataSetChanged();
        }
    }

    private class ContactHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView mContactNameTextView;
        private Contact mContact;

        public ContactHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContactNameTextView = (TextView) itemView;
        }

        public void bindContact(Contact contact) {
            mContact = contact;
            mContactNameTextView.setText(contact.getName());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mContact.getName() + " clicked.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = ContactPagerActivity.newIntent(getActivity(), mContact.getID());
            startActivity(intent);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
        private List<Contact> mContacts;

        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);
            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            Contact contact = mContacts.get(position);
            //holder.mContactNameTextView.setText(contact.getName());
            holder.bindContact(contact);
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }
    }
}
